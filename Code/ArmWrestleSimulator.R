gender1 <- readline(prompt="Gender of person 1 (M/F): ")
if (gender1 == "M"){
  gender1 = 1
} else {
  gender1 = 0
}
length1 <- readline(prompt="Length (cm) of arm of person 1: ")
length1 <- as.integer(length1)
angle1 <- readline(prompt="Angle (degrees) of arm wrestle for person 1: ")
angle1 <- as.integer(angle1)
gender2 <- readline(prompt="Gender of person 2 (M/F): ")
if (gender2 == "M"){
  gender2 = 1
} else {
  gender2 = 0
}
length2 <- readline(prompt="Length (cm) of arm of person 2: ")
length2 <- as.integer(length2)
angle2 <- readline(prompt="Angle (degrees) of arm wrestle for person 2: ")
angle2 <- as.integer(angle2)

n1 = 26
n2 = 26

beta1 = 2173.017
beta1error = 134.190
beta2 = 101.359
beta2error = 7.075

maxForceVal1 = beta1/(length1*sin(angle1*pi/180))+beta2*(gender1)
maxForceVal2 = beta1/(length2*sin(angle2*pi/180))+beta2*(gender2)
                                
partial1B1 = 1/(length1*sin(angle1*pi/180))
partial1B2 = gender1
maxForceError1 = sqrt((partial1B1*beta1error)^2 + (partial1B2*beta2error)^2)
partial2B1 = 1/(length2*sin(angle2*pi/180))
partial2B2 = gender2
maxForceError2 = sqrt((partial2B1*beta1error)^2 + (partial1B2*beta2error)^2)
                                                                            
s1Df = maxForceError1^2/n1;
s2Df = maxForceError2^2/n2;
degF = (s1Df+s2Df)^2/(s1Df^2/(n1-1)+s2Df^2/(n2-1));

tStat = (maxForceVal1-maxForceVal2)/sqrt((maxForceError1^2)/n1+(maxForceError2^2)/n2);


if (maxForceVal1>maxForceVal2){
   pVal = 2*pt(tStat, df=degF, lower.tail = FALSE)
   probability = 100-round(pVal,digits=4)*100
   message(paste0("Probability that person 1 will win: ~", probability, "%"))
} else if (maxForceVal1<maxForceVal2){
  pVal = 2*(1-pt(tStat, df=degF, lower.tail = FALSE))
  probability = 100-round(pVal,digits=4)*100
  message(paste0("Probability that person 2 will win: ~", probability, "%"))
} else {
  message(paste0("Tie!"))
}

