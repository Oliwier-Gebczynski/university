library(ggplot2)
library(reshape2)

data <- hour

casual_rentals_per_hour <- aggregate(casual ~ hr, data = data, FUN = mean)
registered_rentals_per_hour <- aggregate(registered ~ hr, data = data, FUN = mean)

rentals_per_hour <- merge(casual_rentals_per_hour, registered_rentals_per_hour, by = "hr")
colnames(rentals_per_hour) <- c("Hour", "Casual", "Registered")

melted_data <- melt(rentals_per_hour, id.vars = "Hour")

library(ggplot2)
my_palette <- c("#e69f00", "#53777a", "#c02998", "#ffccdd")

ggplot(melted_data, aes(x = factor(Hour), y = value, fill = variable)) +
  geom_bar(stat = "identity", position = "dodge") +
  scale_fill_manual(values = my_palette[3:4]) +
  labs(x = "Hour of the Day", y = "Average Number of Rentals", fill = "User Type") +
  ggtitle("Average Number of Bike Rentals per Hour") +
  theme_minimal()

correlation <- cor(rentals_per_hour$Casual, rentals_per_hour$Registered)
correlation

t_test_result <- t.test(rentals_per_hour$Casual, rentals_per_hour$Registered, paired = TRUE)
t_test_result

model <- aov(cnt ~ casual + registered + Error(hr/(casual + registered)), data = hour)
summary(model)

ggplot(rentals_per_hour, aes(x = Casual, y = Registered)) +
  geom_point() +
  geom_smooth(method = "lm", se = FALSE) +
  labs(x = "Average Casual Rentals", y = "Average Registered Rentals") +
  ggtitle("Relationship between Casual and Registered Rentals") +
  theme_minimal()
