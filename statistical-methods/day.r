install.packages("dplyr")
install.packages("ggplot2")

library(dplyr)
library(ggplot2)
data <- day

# 1 - Correlation between temperature and casual bike rentals
plot(x = data$real_temp, y = data$casual, xlab = "Temperature in degree Celsius", ylab = "Casual Bike Rentals",
     main = "Correlation between temperature and casual bike rentals", xlim = xlim, ylim = ylim, col = "purple", pch = 3)

abline(lm(casual ~ real_temp, data = data), lty = 6, col = "red")

cor_casual <- round(cor(data$casual, data$real_temp), 2)

legend_text <- paste("Correlation: ", cor_casual, sep = "")
legend("topright", legend = legend_text, col = "red", lty = 6)


# 2 - Correlation between temperature and registered bike rentals
plot(x = data$real_temp, y = data$registered, xlab = "Temperature in degree Celsius", ylab = "Registered Bike Rentals",
     main = "Correlation between temperature and registered bike rentals", xlim = xlim, ylim = ylim, col = "orange", pch = 3)

abline(lm(registered ~ real_temp, data = data), lty = 6, col = "blue")

cor_registered <- round(cor(data$registered, data$real_temp), 2)

legend_text <- paste("Correlation: ", cor_registered, sep = "")
legend("topright", legend = legend_text, col = "blue", lty = 6)


data$season <- factor(data$season, levels = 1:4, labels = c("Winter", "Spring", "Summer", "Fall"))
data$holiday <- factor(data$holiday, levels = 0:1, labels = c("Working Day", "Holiday"))
data$weathersit <- factor(data$weathersit, levels = 1:3, labels = c("Clear", "Cloudy", "Rainy"))

# 3 - Boxplot for Total Bike Rentals Vs Season
boxplot(data$cnt ~ data$season,
        main = "Total Bike Rentals Vs Season",
        xlab = "Season",
        ylab = "Total Bike Rentals",
        col = c("coral", "coral1", "coral2", "coral3"))

# 4 - Boxplot for Total Bike Rentals Vs Holiday/Working Day
boxplot(data$cnt ~ data$holiday,
        main = "Total Bike Rentals Vs Holiday/Working Day",
        xlab = "Holiday/Working Day",
        ylab = "Total Bike Rentals",
        col = c("pink", "pink1", "pink2", "pink3"))

# 5 - Boxplot for Total Bike Rentals Vs Weather Situation
boxplot(data$cnt ~ data$weathersit,
        main = "Total Bike Rentals Vs Weather Situation",
        xlab = "Weather Situation",
        ylab = "Total Bike Rentals",
        col = c("purple", "purple1", "purple2", "purple3"))


# 6 - Histogram of Total Bike Rentals
hist(data$cnt, 
     breaks = 30, 
     col = "skyblue", 
     main = "Histogram of Total Bike Rentals", 
     xlab = "Total Bike Rentals", 
     ylab = "Frequency", 
     probability = TRUE)
lines(density(data$cnt), col = "red", lwd = 2)

ggplot(data, aes(x = cnt)) + 
  geom_histogram(aes(y = ..density..), bins = 30, fill = "skyblue", color = "black") +
  geom_density(color = "red", size = 1) +
  labs(title = "Histogram of Total Bike Rentals with Density Curve", 
       x = "Total Bike Rentals", 
       y = "Density") +
  theme_minimal()