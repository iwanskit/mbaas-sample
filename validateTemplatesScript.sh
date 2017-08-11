#!/bin/bash
set -e
cd src/main/resources/templates

for file in ./*
do
        echo ${file}
        aws cloudformation validate-template --template-body file://${file}
done
