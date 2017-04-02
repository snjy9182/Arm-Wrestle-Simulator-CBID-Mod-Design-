f= file.choose()
data = read.csv(f)
View(data)

relmaxforcemodel = aov(data$Rel..Max.Force~data$Angle)
anova(relmaxforcemodel)
plot(relmaxforcemodel)

boxplot(data$Rel..Max.Force~data$Angle, main="Relative Max Force Predicted by Angle (p<0.001)", ylab="Relative Max Force (%)", xlab="Angle")
