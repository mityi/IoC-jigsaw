import ru.example.provider.ExProvider;
import ru.example.provider.impl.ExProviderImpl;

module ru.example.provider.first {
    requires ru.example.provider.zero;
    provides ExProvider with ExProviderImpl;
}