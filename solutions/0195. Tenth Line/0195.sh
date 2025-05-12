# Read from the file file.txt and output the tenth line to stdout.

i=0;
while read p; do
  if [ $i == 9 ]
  then
	echo "$p"
  fi
  ((i++))
  done <file.txt