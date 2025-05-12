#!/bin/bash

declare -A words

myfile=$(<words.txt)
myfile=`echo ${myfile} | tr '\r\n\t' ' ' | xargs`
for w in ${myfile}
do
  if [[ -v words[${w}] ]]; then
      words[${w}]=$((words[${w}]+1))
  else
      words[${w}]=1
  fi
done
for k in "${!words[@]}"
do
    echo $k ${words["$k"]}
done |
sort -rn -k2