# Super-Duper-Engine
Super-Duper-Engine for mathematical equations and conversions.

## Build & Run
```
mvn package
docker build -t super-duper-engine .
docker container run -it -p 8081:8080 super-duper-engine
```

## How it works

### Convert between different units of length

Got to: http://localhost:8081/super-duper-engine/convert/length

This will return a JSON string with all the supported units, for example:

```json
{
  "units": [
    {
      "name": "Millimeter",
      "abbreviation": "mm"
    },
    {
      "name": "Meter",
      "abbreviation": "m"
    },
    {
      "name": "Kilometer",
      "abbreviation": "km"
    }
  ]
}
```

Choose the unit to convert from and the unit to convert to and supply the value to convert:

http://localhost:8081/super-duper-engine/convert/length?from=m&to=mm&value=44

This will return the answer as a JSON string:

```json
{
  "value": "44000.0",
  "unit": "mm"
}
```
### Convert between different units of temperature

Got to: http://localhost:8081/super-duper-engine/convert/temperature

This will return a JSON string with all the supported units, for example:

```json
{
  "units": [
    {
      "name": "Celsius",
      "abbreviation": "C"
    },
    {
      "name": "Kelvin",
      "abbreviation": "K"
    },
    {
      "name": "Fahrenheit",
      "abbreviation": "F"
    }
  ]
}
```

Choose the unit to convert from and the unit to convert to and supply the value to convert:

http://localhost:8081/super-duper-engine/convert/temperature?from=C&to=K&value=44

This will return the answer as a JSON string:

```json
{
  "value": "317.15",
  "unit": "K"
}
```