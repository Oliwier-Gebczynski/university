library(ggplot2)
library(reshape2)

data <- hour
my_palette <- c("#e69f00", "#53777a", "#c02998","#ffccdd")

total_rentals_per_hour <- aggregate(cnt ~ hr, data = data, FUN = mean)
casual_rentals_per_hour <- aggregate(casual ~ hr, data = data, FUN = mean)
registered_rentals_per_hour <- aggregate(registered ~ hr, data = data, FUN = mean)
correlation <- cor(data$temp, data$cnt)
data$temperature_celsius <- data$temp * (39 - (-8)) + (-8)

correlation_celsius <- cor(data$temperature_celsius, data$cnt)

cor_test_celsius <- cor.test(data$temperature_celsius, data$cnt)
print(cor_test_celsius)

cat("Correlation coefficient (Celsius):", correlation_celsius, "\n")

model_celsius <- lm(cnt ~ temperature_celsius, data = data)

summary(model_celsius)

ggplot(data, aes(x = temperature_celsius, y = cnt)) +
  geom_point() +
  geom_smooth(method = "lm", se = FALSE, color = "blue") +
  labs(x = "Temperature (Celsius)", y = "Total Rentals", title = "Temperature (Celsius) vs Total Rentals")