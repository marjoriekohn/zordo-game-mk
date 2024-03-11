#!/bin/sh


stash_commit="$(git stash create)"
git reset —-hard

gradle build
gradle lint

status=$?

if [[ -n "${stash_commit}" ]]
then git stash apply "${stash_commit}"
fi

echo "*****Done with unit tests******"

exit $status