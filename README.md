### Running Java Server:

```
mvn compile
```
```
mvn -e exec:java -Dexec.mainClass=example.MovieServiceServer -Dexec.args='<Usage: 1=sync, 2=async>'
```

### Running Java Client

```
mvn -e exec:java -Dexec.mainClass=example.MovieServiceServer -Dexec.args='Usage: <client mode 1=Java, 2=Python> <conn mode 1=netty, 2=http> <rpc mode 1=sync, 2=async> <iterations>'
```

### Running Python Server

Navigate to src/main/python. Run
```
python start_server.py
```