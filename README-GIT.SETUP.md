#!/bin/bash
# Git All-in-One Setup Script for hytte-monitor

# Navigate to repo root
cd ~/projects_github/hytte-monitor || { echo "Repo not found!"; exit 1; }

echo "=== Setting per-repo Git identity ==="
git config user.name "nilstore"
git config user.email "nilstores@gmail.com"
echo "User name: $(git config user.name)"
echo "User email: $(git config user.email)"

echo "=== Configuring remote to use SSH ==="
git remote set-url origin git@github.com:nilstore/hytte-monitor.git
git remote -v

# Check if SSH key exists
if [ ! -f "$HOME/.ssh/id_ed25519" ]; then
    echo "=== SSH key not found, generating new one ==="
    ssh-keygen -t ed25519 -C "nilstores@gmail.com" -f "$HOME/.ssh/id_ed25519" -N ""
    echo "=== Public key (add to GitHub SSH keys) ==="
    cat "$HOME/.ssh/id_ed25519.pub"
    echo "Please add this key to GitHub, then re-run the script."
    exit 0
fi

echo "=== Testing SSH connection to GitHub ==="
ssh -T git@github.com

echo "=== Fetching updates from remote ==="
git fetch origin

echo "=== Pulling latest changes ==="
git pull origin main

echo "=== Pushing current branch ==="
git push -u origin main

echo "=== Done! Repository is set up with SSH and per-repo identity ==="


