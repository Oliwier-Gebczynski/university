std::vector<double> tab(size);

    size_t i = 0;
    for ( auto& el : tab){
        std::cout << ++i << ". ";
        std::cin >> el;
    }

    std::cout << "[ ";
    for( const auto el : tab){
        std::cout << el << " ";
    }
    std::cout << "]";