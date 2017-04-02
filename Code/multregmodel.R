f= file.choose()
data = read.csv(f)
View(data)

A = 1/(data$L*sin(data$Theta*pi/180))

summary(fit<-lm(MaxF~A+Gender+0, data=data))
