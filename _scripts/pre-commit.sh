#!/bin/sh

echo "*****Running unit tests******"

./gradlew test

status=$?

if [[ -n "${stash_commit}" ]]
then git stash apply "${stash_commit}"
fi

echo "*****Done with unit tests******"

exit $status