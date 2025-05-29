import os
import re
from collections import defaultdict

def list_invalid_files():
    # Root directory containing all solution folders
    solutions_dir = "solutions"
    # Regex pattern for valid filenames (e.g., 000.cpp, 001.py)
    valid_filename_pattern = re.compile(r'^\d{3}\.[a-zA-Z]+$')

    # Store files to be deleted, grouped by directory
    files_to_delete = defaultdict(list)
    total_files = 0

    try:
        subdirs = [d for d in os.listdir(solutions_dir) if os.path.isdir(os.path.join(solutions_dir, d))]
    except FileNotFoundError:
        print(f"Error: {solutions_dir} directory not found")
        return None

    # First pass: collect all files to be deleted
    for subdir in sorted(subdirs):
        subdir_path = os.path.join(solutions_dir, subdir)
        files = os.listdir(subdir_path)

        for file in sorted(files):
            # Skip directories
            if os.path.isdir(os.path.join(subdir_path, file)):
                continue

            # Check if filename matches the pattern
            if not valid_filename_pattern.match(file):
                files_to_delete[subdir].append(file)
                total_files += 1

    return files_to_delete, total_files

def delete_files(files_to_delete):
    solutions_dir = "solutions"
    deleted_count = 0

    for subdir, files in files_to_delete.items():
        for file in files:
            file_path = os.path.join(solutions_dir, subdir, file)
            try:
                os.remove(file_path)
                print(f"Deleted: {file_path}")
                deleted_count += 1
            except OSError as e:
                print(f"Error deleting {file_path}: {e}")

    print(f"\nCleanup completed. Deleted {deleted_count} files.")

def main():
    # First, list all files that would be deleted
    result = list_invalid_files()
    if result is None:
        return

    files_to_delete, total_files = result

    if total_files == 0:
        print("No invalid files found.")
        return

    # Print the files that would be deleted
    print("The following files do not match the pattern '000.ext' and will be deleted:\n")
    for subdir, files in files_to_delete.items():
        print(f"In {subdir}:")
        for file in files:
            print(f"  - {file}")

    print(f"\nTotal files to delete: {total_files}")

    # Ask for confirmation
    response = input("\nDo you want to delete these files? (y/n): ")
    if response.lower() == 'y':
        delete_files(files_to_delete)
    else:
        print("Operation cancelled.")

if __name__ == "__main__":
    main()