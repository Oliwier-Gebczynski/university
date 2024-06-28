library(ggplot2)
library(reshape2)

data <- hour
my_palette <- c("#e69f00", "#53777a", "#c02998", "#ffccdd")

casual_rentals_per_hour_season <- aggregate(casual ~ hr + season, data = data, FUN = mean)
registered_rentals_per_hour_season <- aggregate(registered ~ hr + season, data = data, FUN = mean)

rentals_per_hour_season <- merge(casual_rentals_per_hour_season, registered_rentals_per_hour_season, by = c("hr", "season"))
colnames(rentals_per_hour_season) <- c("Hour", "Season", "Casual", "Registered")

melted_data_season <- melt(rentals_per_hour_season, id.vars = c("Hour", "Season"))

ggplot(melted_data_season, aes(x = factor(Hour), y = value, fill = variable)) +
  geom_bar(stat = "identity", position = position_dodge(width = 0.9), width = 0.8) +
  scale_fill_manual(values = my_palette[3:4]) +
  scale_x_discrete(breaks = seq(0, 23, by = 5)) +
  labs(x = "Hour of the Day", y = "Average Number of Rentals", fill = "User Type") +
  ggtitle("Average Number of Bike Rentals per Hour by Season") +
  facet_wrap(~ Season, nrow = 2, labeller = labeller(Season = c('1' = 'Winter', '2' = 'Spring', '3' = 'Summer', '4' = 'Fall'))) +
  theme_minimal()
