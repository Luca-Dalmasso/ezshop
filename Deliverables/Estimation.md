# Project Estimation  
Authors 
- 		 Dalmasso Luca s281316
 		 Kitou Mgbatou Osee Patrik s288425
 		 Mistruzzi Luca Guglielmo s292623
 		 Protopapa Matteo s290151
Date: 26/04/2021
Version: 1.0
# Contents
- [Project Estimation](#project-estimation)
- [Contents](#contents)
- [Estimation approach](#estimation-approach)
  - [Estimate by product decomposition](#estimate-by-product-decomposition)
    - [PBS table](#pbs-table)
  - [Estimate by activity decomposition](#estimate-by-activity-decomposition)
    - [WBS table](#wbs-table)
    - [Gantt chart](#gantt-chart)
# Estimation approach

## Estimate by product decomposition

### PBS table
|                                                                                                         | Estimate       |
| ------------------------------------------------------------------------------------------------------- | -------------- |
| NC =  Estimated number of classes to be developed                                                       | 40             |
| A = Estimated average size per class, in LOC                                                            | 50             |
| S = Estimated size of project, in LOC (= NC * A)                                                        | 2000           |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)                    | 200            |
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro)                                     | 3000           |
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 6 days, 2 hour |

## Estimate by activity decomposition

### WBS table
| Activity name        | Estimated effort (person hours) |
| -------------------- | ------------------------------- |
| Requiriment document | 80                              |
| GUI prototype design | 20                              |
| Design document      | 50                              |
| Coding               | 50                              |
| Testing              | 20                              |




### Gantt chart

```
@startuml
scale 2
Project starts 2021-04-01
saturday are close
sunday are close
[Requiriment document] lasts 6 days
then [GUI prototype design] lasts 2 days
then [Design document] lasts 4 days
then [Coding                   ] lasts 4 days
then [Testing] lasts 4 days

[Requiriment document] ends 2021-04-08
[GUI prototype design] ends 2021-04-12
[Design document] ends 2021-04-16
[Coding                   ] ends 2021-04-22
[Testing] ends 2021-04-23
@enduml

```
