f= file.choose()
data = read.csv(f)
View(data)

t.test(data$rel45, data$rel60)
t.test(data$rel60, data$rel75)
t.test(data$rel75, data$rel90)

