import static ru.nsu.ramazanova.ModelConfiguration.*



tasks {
  task(0, 'Task_1_1_1', 1.0)
  task(1, 'Task_1_2_1', 2.0)
  task(2, 'Task_1_2_2', 3.5)
  task(3, 'Task_1_2_3', 4.0)
  task(4, 'Task_1_3_1', 1.5)
}

group {
  student('nosikmy', 'Ramazanova Amina Rustamovna', 'https://github.com/nosikmy/OOP')
}

controlPoints {
  controlPoint('soft_1_1_1', '14.09.2022')
  controlPoint('hard_1_1_1', '21.09.2022')
  controlPoint('soft_1_2_1', '28.09.2022')
  controlPoint('hard_1_2_1', '05.10.2022')
  controlPoint('soft_1_2_2', '12.10.2022')
  controlPoint('hard_1_2_2', '19.10.2022')

}

println "$tasksList"
println "$groupList"
println "$controlPointsList"