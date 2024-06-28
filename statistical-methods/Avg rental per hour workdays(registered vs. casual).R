# Load required libraries
library(ggplot2)
library(reshape2)

data <- hour
my_palette <- c("#e69f00", "#53777a", "#c02998", "#ffccdd")

casual_rentals_per_hour_workingday <- aggregate(casual ~ hr + workingday, data = data, FUN = mean)
registered_rentals_per_hour_workingday <- aggregate(registered ~ hr + workingday, data = data, FUN = mean)

rentals_per_hour_workingday <- merge(casual_rentals_per_hour_workingday, registered_rentals_per_hour_workingday, by = c("hr", "workingday"))
colnames(rentals_per_hour_workingday) <- c("Hour", "WorkingDay", "Casual", "Registered")

melted_data_workingday <- melt(rentals_per_hour_workingday, id.vars = c("Hour", "WorkingDay"))

ggplot(melted_data_workingday, aes(x = factor(Hour), y = value, fill = variable)) +
  geom_bar(stat = "identity", position = position_dodge(width = 0.8)) +
  scale_fill_manual(values = my_palette[3:4]) +
  scale_x_discrete(breaks = seq(0, 23, by = 5)) +
  labs(x = "Hour of the Day", y = "Average Number of Rentals", fill = "User Type") +
  ggtitle("Average Number of Bike Rentals per Hour by Working Day Status") +
  facet_wrap(~ WorkingDay, labeller = labeller(WorkingDay = c('0' = 'Non-Working Day', '1' = 'Working Day'))) +
  theme_minimal() +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))
