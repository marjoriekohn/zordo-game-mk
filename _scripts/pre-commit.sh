#!/bin/sh

./gradlew build

status=$?

if [[ -n "${stash_commit}" ]]
then git stash apply "${stash_commit}"
fi

exit $status