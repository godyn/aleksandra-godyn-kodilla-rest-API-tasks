#!/usr/bin/env bash
fail(){
echo "There were errors!"
}

openLink(){
open -a Google\ Chrome http://localhost:8080/crud/v1/task/getTasks
}

if ./runcrud.sh; then
    openLink
else
    fail
fi