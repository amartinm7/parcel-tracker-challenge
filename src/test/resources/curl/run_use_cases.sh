#!/usr/bin/env bash

curl -X POST "http://localhost:8085/api/register" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"reference\":\"ABCD123456\", \"parcels\" : [ { \"weight\":1, \"width\": 10, \"height\": 10, \"lenght\": 10 }, { \"weight\":2, \"width\": 20, \"height\": 20, \"lenght\": 20 } ]}"
echo
curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"status\":\"WAITING_IN_HUB\", \"parcels\":2, \"weight\":null, \"reference\":\"ABCD123456\"}"
echo
curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"status\":\"WAITING_IN_HUB\", \"parcels\":2, \"weight\":2, \"reference\":\"ABCD123456\"}"
echo
curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"status\":\"WAITING_IN_HUB\", \"parcels\":1, \"weight\":15, \"reference\":\"ABCD123456\"}"
echo
curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"status\":\"WAITING_IN_HUB\", \"parcels\":2, \"weight\":30, \"reference\":\"ABCD123456\"}"
echo
curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"status\":\"DELIVERED\", \"parcels\":2, \"weight\":2, \"reference\":\"ABCD123456\"}"
echo
curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"status\":\"DELIVERED\", \"parcels\":2, \"weight\":2, \"reference\":\"ABCD123456\"}"
echo
curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"status\":\"DELIVERED\", \"parcels\":2, \"weight\":30, \"reference\":\"EFGH123456\"}"
echo
curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"status\":\"DELIVERED\", \"parcels\":null, \"weight\":30, \"reference\":\"ABCD123456\"}"
echo