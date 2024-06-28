library(ggplot2)
library(reshape2)

data <- hour

data$temperature_celsius <- data$temp * (39 - (-8)) + (-8)

perform_chi_squared_test <- function(data) {
  num_bins <- floor(sqrt(length(data)))
  
  hist_data <- hist(data, breaks = num_bins, plot = FALSE)
  observed_frequencies <- hist_data$counts
  bin_midpoints <- hist_data$mids
  
  expected_frequencies <- dnorm(bin_midpoints, mean(data), sd(data)) * length(data) * diff(hist_data$breaks)[1]
  
  chi_squared_test <- chisq.test(x = observed_frequencies, p = expected_frequencies / sum(expected_frequencies))
  return(chi_squared_test)
}

chi_squared_casual <- perform_chi_squared_test(data$casual)

chi_squared_registered <- perform_chi_squared_test(data$registered)

cor_casual <- cor.test(data$temperature_celsius, data$casual, method = "pearson")
cor_registered <- cor.test(data$temperature_celsius, data$registered, method = "pearson")

t_test_result <- t.test(data$casual, data$registered, paired = TRUE)

list(
  chi_squared_casual = chi_squared_casual,
  chi_squared_registered = chi_squared_registered,
  cor_casual = cor_casual,
  cor_registered = cor_registered,
  t_test_result = t_test_result
)

