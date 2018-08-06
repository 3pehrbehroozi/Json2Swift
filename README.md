# Json2Swift
### Simple cli tool converting json object to swift models.

## Download
Download the latest jar [here](https://github.com/3pehrbehroozi/Json2Swift/releases/latest)


## Usage
```bash
java -jar Json2Swift.jar -s /path/to/json/file -d /path/to/SwiftFile.swift
```

## Options
| short flag | long flag         | Description                                                                                              |
|:----------:|-------------------|----------------------------------------------------------------------------------------------------------|
| -s         | --source          | path to source json file                                                                                 |
| -d         | --destination     | path to destination swift file (replace if already exists)                                               |
| -b         | --base-class-name | root class name (default is the output swift file name. use this flag if it needed to be different name) |
| -n         | --name-map        | path to name map file (coming soon)                                                                      |

## Copyright

```
Copyright 2018 Sepehr Behroozi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```