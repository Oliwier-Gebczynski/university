library(ggplot2)

data <- hour
my_palette <- c("#e69f00", "#53777a", "#c02942", "#ffccdd")

total_rentals_per_hour_season <- aggregate(cnt ~ hr + season, data = data, FUN = mean)

ggplot(data = total_rentals_per_hour_season, aes(x = factor(hr), y = cnt)) +
  geom_bar(stat = "identity", color = my_palette[1], fill = my_palette[1]) +
  scale_x_discrete(breaks = seq(0, 23, by = 5)) + 
  labs(title = "Rental Bike Usage Dependence on Hour (All Users)",
       x = "Hour (0-23)", 
       y = "Average Number of Rentals") +
  facet_wrap(~ season, nrow = 2, labeller = labeller(season = c('1' = 'Winter', '2' = 'Spring', '3' = 'Summer', '4' = 'Fall'))) +
  theme_minimal()
