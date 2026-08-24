#!/usr/bin/env python3
"""
extract_structure.py

Walks a codebase directory and prints/saves its folder + file structure
as a clean tree, optionally with file sizes and line counts.

Usage:
    python extract_structure.py                     # scan current directory
    python extract_structure.py /path/to/project     # scan a specific path
    python extract_structure.py --output tree.txt    # save to a file
    python extract_structure.py --sizes              # show file sizes
    python extract_structure.py --lines              # show line counts for text files
    python extract_structure.py --max-depth 3        # limit recursion depth
    python extract_structure.py --ext .py .ts .md    # only include these extensions
"""

import argparse
import os
import sys

# Folders that are almost always noise — skipped by default
#DEFAULT_EXCLUDES = {
   # "__pycache__", ".git", ".venv", "venv", "env",
  #  "node_modules", ".mypy_cache", ".pytest_cache",
 #   ".idea", ".vscode", "dist", "build", ".egg-info",
#    ".dvc", ".dvc_cache", "mlruns", ".ipynb_checkpoints",
#}


# Folders that are almost always noise — skipped by default
DEFAULT_EXCLUDES = {
    ".git",
  #  "node_modules", ".mypy_cache", ".pytest_cache",
 #   ".idea", ".vscode", "dist", "build", ".egg-info",
#    ".dvc", ".dvc_cache", "mlruns", ".ipynb_checkpoints",
}


def human_size(num_bytes: int) -> str:
    """Convert bytes to a human-readable string."""
    for unit in ["B", "KB", "MB", "GB"]:
        if num_bytes < 1024:
            return f"{num_bytes:.0f}{unit}"
        num_bytes /= 1024
    return f"{num_bytes:.1f}TB"


def count_lines(filepath: str) -> int | None:
    """Count lines in a text file. Returns None if file is binary/unreadable."""
    try:
        with open(filepath, "r", encoding="utf-8", errors="strict") as f:
            return sum(1 for _ in f)
    except (UnicodeDecodeError, OSError):
        return None


def should_skip_dir(dirname: str, excludes: set[str]) -> bool:
    return dirname in excludes or dirname.startswith(".") and dirname not in {".github"}


def build_tree(
    root: str,
    excludes: set[str],
    show_sizes: bool,
    show_lines: bool,
    extensions: set[str] | None,
    max_depth: int | None,
) -> list[str]:
    """
    Returns a list of strings representing the tree, ready to print or write.
    """
    lines: list[str] = []
    root = os.path.abspath(root)
    lines.append(f"{os.path.basename(root) or root}/")

    def walk(current_path: str, prefix: str, depth: int):
        if max_depth is not None and depth > max_depth:
            return

        try:
            entries = sorted(
                os.listdir(current_path),
                key=lambda e: (not os.path.isdir(os.path.join(current_path, e)), e.lower()),
            )
        except PermissionError:
            lines.append(f"{prefix}└── [permission denied]")
            return

        # Filter out excluded dirs and (optionally) filter files by extension
        filtered = []
        for entry in entries:
            full = os.path.join(current_path, entry)
            if os.path.isdir(full):
                if should_skip_dir(entry, excludes):
                    continue
                filtered.append(entry)
            else:
                if extensions:
                    _, ext = os.path.splitext(entry)
                    if ext not in extensions:
                        continue
                filtered.append(entry)

        for i, entry in enumerate(filtered):
            full = os.path.join(current_path, entry)
            is_last = i == len(filtered) - 1
            connector = "└── " if is_last else "├── "
            extension_prefix = "    " if is_last else "│   "

            if os.path.isdir(full):
                lines.append(f"{prefix}{connector}{entry}/")
                walk(full, prefix + extension_prefix, depth + 1)
            else:
                suffix_parts = []
                if show_sizes:
                    try:
                        suffix_parts.append(human_size(os.path.getsize(full)))
                    except OSError:
                        suffix_parts.append("?")
                if show_lines:
                    nlines = count_lines(full)
                    suffix_parts.append(f"{nlines} lines" if nlines is not None else "binary")
                suffix = f"  ({', '.join(suffix_parts)})" if suffix_parts else ""
                lines.append(f"{prefix}{connector}{entry}{suffix}")

    walk(root, "", 1)
    return lines


def main():
    parser = argparse.ArgumentParser(
        description="Extract and display a codebase's folder/file structure as a tree."
    )
    parser.add_argument(
        "path", nargs="?", default=".", help="Root directory to scan (default: current dir)"
    )
    parser.add_argument(
        "--output", "-o", default=None, help="Write the tree to this file instead of stdout"
    )
    parser.add_argument(
        "--sizes", action="store_true", help="Show file sizes"
    )
    parser.add_argument(
        "--lines", action="store_true", help="Show line counts for text files"
    )
    parser.add_argument(
        "--max-depth", type=int, default=None, help="Maximum folder depth to recurse into"
    )
    parser.add_argument(
        "--ext", nargs="*", default=None,
        help="Only include files with these extensions, e.g. --ext .py .ts .md"
    )
    parser.add_argument(
        "--exclude", nargs="*", default=[],
        help="Additional folder names to exclude beyond the defaults"
    )
    parser.add_argument(
        "--no-default-excludes", action="store_true",
        help="Do not skip common noise folders (__pycache__, .git, node_modules, etc.)"
    )

    args = parser.parse_args()

    if not os.path.isdir(args.path):
        print(f"Error: '{args.path}' is not a directory.", file=sys.stderr)
        sys.exit(1)

    excludes = set() if args.no_default_excludes else set(DEFAULT_EXCLUDES)
    excludes.update(args.exclude)

    extensions = None
    if args.ext:
        # normalize to make sure each starts with a dot
        extensions = {e if e.startswith(".") else f".{e}" for e in args.ext}

    tree_lines = build_tree(
        root=args.path,
        excludes=excludes,
        show_sizes=args.sizes,
        show_lines=args.lines,
        extensions=extensions,
        max_depth=args.max_depth,
    )

    output_text = "\n".join(tree_lines)

    if args.output:
        with open(args.output, "w", encoding="utf-8") as f:
            f.write(output_text + "\n")
        print(f"Structure written to {args.output} ({len(tree_lines)} entries)")
    else:
        print(output_text)


if __name__ == "__main__":
    main()
