# Table structure

| Field Name   | Data Type | Length | Nullable | Unique |
| ------------ | --------- | ------ | :------: | :----: |
| code         | varchar   | 12     |    -     |   x    |
| city_name    | varchar   | 128    |    -     |        |
| region_name  | varchar   | 128    |    x     |        |
| country_name | varchar   | 64     |    -     |        |
| country_code | char      | 2      |    -     |        |
| enable       | int       |        |          |        |
| trashed      | int       |        |          |        |