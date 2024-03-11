#!/bin/sh


stash_commit="$(git stash create)"

status=$?

if [[ -n "${stash_commit}" ]]
then gradle build && gradle lint && git stash apply "${stash_commit}"
fi

exit $status