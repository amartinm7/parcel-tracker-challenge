#!/usr/bin/env bash

curl -X POST "http://localhost:8085/api/register" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"reference\":\"ABCD123456\", \"parcels\" : [ { \"weight\":1, \"width\": 10, \"height\": 10, \"lenght\": 10 }, { \"weight\":2, \"width\": 20, \"height\": 20, \"lenght\": 20 } ]}"

curl -X PUT "http://localhost:8085/api/push" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"parcels\": 1, \"reference\": \"string\", \"status\": \"DELIVERED\", \"weight\": 0}"