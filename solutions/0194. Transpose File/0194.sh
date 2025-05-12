declare -A matrix


i=0
j=0
while IFS= read -r line; do
#   echo "$line"
    j=0
    for word in ${line[@]} ; do
        matrix[$i,$j]=$word;
	j=$((j+1))
    done
    i=$((i+1))
    #for ((j=1;j<=num_columns;j++)) do
    #      matrix[$i,$j]=$RANDOM
    #done
done < file.txt

num_rows=$((i))
num_columns=$((j))

for ((j=0;j<num_columns;j++)) do
    for ((i=0;i<num_rows;i++)) do
        printf "%s" ${matrix[$i,$j]}
		if (( $i < $num_rows - 1 )); then
			printf " "
		fi
    done
    echo
done