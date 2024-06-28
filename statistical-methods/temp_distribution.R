library(ggplot2)
library(reshape2)

data <- hour
my_palette <- c("#e69f00", "#53777a", "#c02998","#ffccdd")

total_rentals_per_hour <- aggregate(cnt ~ hr, data = data, FUN = mean)
casual_rentals_per_hour <- aggregate(casual ~ hr, data = data, FUN = mean)
registered_rentals_per_hour <- aggregate(registered ~ hr, data = data, FUN = mean)
correlation <- cor(data$temp, data$cnt)
data$temperature_celsius <- data$temp * (39 - (-8)) + (-8)

ggplot(data, aes(x = temperature_celsius)) + 
  geom_density(fill = "blue", color = "black") +
  labs(x = "Temperature (Celsius)", y = "Density", title = "Distribution of Temperature")