library(ggplot2)

data <- hour
my_palette <- c("#e69f00", "#53777a")

total_rentals_per_hour_workingday <- aggregate(cnt ~ hr + workingday, data = data, FUN = mean)

ggplot(data = total_rentals_per_hour_workingday, aes(x = factor(hr), y = cnt, fill = factor(workingday))) +
  geom_bar(stat = "identity", position = position_dodge(width = 0.8)) +
  scale_fill_manual(values = my_palette, labels = c('Non-Working Day', 'Working Day')) +
  scale_x_discrete(breaks = seq(0, 23, by = 5)) + 
  labs(title = "Rental Bike Usage Dependence on Hour (All Users)",
       x = "Hour (0-23)", 
       y = "Average Number of Rentals",
       fill = "Day Type") +
  theme_minimal() +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))
