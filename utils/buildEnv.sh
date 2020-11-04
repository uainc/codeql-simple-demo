#!/bin/sh

sudo apt install gcc-8-aarch64-linux-gnu
sudo apt install g++-8-aarch64-linux-gnu
sudo apt install qemu
sudo apt install qemu-user
sudo apt install gdb-multiarch

# aarch64-linux-gnu-g++-8 -o arm64main arm64main.cpp -static
# file arm64main  
# aarch64-linux-gnu-objdump -d arm64main | aarch64-linux-gnu-c++filt
# qemu-aarch64 ./arm64main
