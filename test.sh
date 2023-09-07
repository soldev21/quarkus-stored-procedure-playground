file_suffix=$1
num_of_iterations=$2

res_file=./curl_out_$file_suffix.log

echo "num_of_iterations: $num_of_iterations"
rm -f $res_file

for (( c=0; c<$num_of_iterations; c++ )) do
  _out=$(curl "http://localhost:8080/api/test/func");
  echo "${_out}" >> $res_file;
done

# bash test.sh 1 100000