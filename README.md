# pact-contract-testing
This is a sample pact-contract-testing written in Java.

# stock-market module
This the a small REST microservice, which provides stock-price services based on marketName and stockName.

# stock-market-consumer module
This module acts as a consumer of the RESTful service and generate the Pact contact file in ./pacts directory.

# verifier module
This module verify the generated pact contact file against the microservice(provider).
