#!/bin/sh

./gradlew build
./gradlew lint

status=$?

if [[ -n "${stash_commit}" ]]
then git stash apply "${stash_commit}"
fi

exit $status