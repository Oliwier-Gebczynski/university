library(ggplot2)
library(reshape2)

data <- hour
my_palette <- c("#e69f00", "#53777a", "#c02998","#ffccdd")

casual_rentals_per_hour <- aggregate(casual ~ hr, data = data, FUN = mean)
registered_rentals_per_hour <- aggregate(registered ~ hr, data = data, FUN = mean)

rentals_per_hour <- merge(casual_rentals_per_hour, registered_rentals_per_hour, by = "hr")
colnames(rentals_per_hour) <- c("Hour", "Casual", "Registered")
melted_data <- melt(rentals_per_hour, id.vars = "Hour")

ggplot(melted_data, aes(x = factor(Hour), y = value, fill = variable)) +
  geom_bar(stat = "identity", position = "dodge") +
  scale_fill_manual(values = my_palette[3:4]) +
  labs(x = "Hour of the Day", y = "Average Number of Rentals", fill = "User Type") +
  ggtitle("Average Number of Bike Rentals per Hour") +
  theme_minimal()

