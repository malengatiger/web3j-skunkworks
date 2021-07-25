echo '🔵 🔵 Starting to compile Solidity contracts ..... 🔵 🔵 '
source $HOME/.web3j/source.sh
cd /Users/aubs/WORK/WEB3J_LEARNING/myweb3

solc src/main/resources/solidity/HelloWorld.sol --bin --abi --overwrite -o src/main/resources/solidity/

echo '🔵 🔵 Starting to generate Java wrappers ..... 🔵 🔵 '
web3j generate solidity -a src/main/resources/solidity/HelloWorld.abi -b src/main/resources/solidity/HelloWorld.bin -c HelloWorld -p contracts -o src/main/java/com/boha/myweb3/

echo '🔵 🔵 Completed the job, Boss! ..... 🔵 🔵 '