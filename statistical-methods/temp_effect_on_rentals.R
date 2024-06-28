library(ggplot2)
library(reshape2)

data <- hour
my_palette <- c("#e69f00", "#53777a", "#c02998","#ffccdd")

total_rentals_per_hour <- aggregate(cnt ~ hr, data = data, FUN = mean)
casual_rentals_per_hour <- aggregate(casual ~ hr, data = data, FUN = mean)
registered_rentals_per_hour <- aggregate(registered ~ hr, data = data, FUN = mean)
correlation <- cor(data$temp, data$cnt)
data$temperature_celsius <- data$temp * (39 - (-8)) + (-8)

casual_plot <- ggplot(data, aes(x = temperature_celsius, y = casual)) +
  geom_point(color = "blue") +
  labs(x = "Temperature (Celsius)", y = "Casual Rentals", title = "Temperature Effect on Casual Rentals")

registered_plot <- ggplot(data, aes(x = temperature_celsius, y = registered)) +
  geom_point(color = "red") +
  labs(x = "Temperature (Celsius)", y = "Registered Rentals", title = "Temperature Effect on Registered Rentals")

library(gridExtra)
grid.arrange(casual_plot, registered_plot, ncol = 2)