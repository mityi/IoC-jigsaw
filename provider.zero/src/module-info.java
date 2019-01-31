import ru.example.provider.ExProvider;

module ru.example.provider.zero {
    requires transitive ru.iocmodule;
    // -> все модули транзетивно получали зависимости  тк код внутри и нет модуля в контектак то пишем здесь
//    exports ru.iocmodule;
//    exports ru.example.annotation;
    exports ru.example.provider;
    exports ru.example.annotation;

    uses ExProvider;
}