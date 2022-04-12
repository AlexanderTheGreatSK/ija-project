#!/bin/sh

# This script downloads JavaFX library, unzip it and remove old .zip file

# Author: Alexander Okrucký <xokruc00>

wget https://download2.gluonhq.com/openjfx/17.0.2/openjfx-17.0.2_linux-x64_bin-sdk.zip
unzip openjfx-17.0.2_linux-x64_bin-sdk.zip
rm openjfx-17.0.2_linux-x64_bin-sdk.zip
