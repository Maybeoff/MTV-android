# MTV Android App

Приложение для воспроизведения HLS потока телеканала MTV.

## Возможности

- Воспроизведение HLS потока в реальном времени
- Полноэкранный режим
- Автоматическое воспроизведение при запуске
- Поддержка Android 5.0+ (API 21+)

## Технологии

- **Kotlin** - основной язык программирования
- **Jetpack Compose** - современный UI фреймворк
- **ExoPlayer** - мощный медиа плеер
- **Material 3** - дизайн система

## Запуск

1. Откройте проект в Android Studio
2. Подключите Android устройство или запустите эмулятор
3. Нажмите "Run" (▶️) или используйте `Shift + F10`
4. Приложение автоматически откроется и начнет воспроизведение

## Сборка из командной строки

```bash
# Windows
.\gradlew.bat assembleDebug

# Linux/Mac
./gradlew assembleDebug
```

## Разрешения

Приложение требует следующие разрешения:
- `INTERNET` - для доступа к HLS потоку
- `ACCESS_NETWORK_STATE` - для проверки состояния сети

## HLS Поток

Приложение воспроизводит поток с адреса:
`https://tv.maybeyoou.tw1.su/hls/stream.m3u8`

## Структура проекта

```
app/src/main/java/ru/mby/mtv/
├── MainActivity.kt          # Главная активность
├── VideoPlayer.kt           # Компонент видео плеера
└── ui/theme/                # Тема приложения
    ├── Color.kt
    ├── Theme.kt
    └── Type.kt
```

## Зависимости

- `androidx.media3:media3-exoplayer:1.3.1` - ExoPlayer
- `androidx.media3:media3-ui:1.3.1` - UI компоненты
- `androidx.media3:media3-common:1.3.1` - общие компоненты
- `androidx.media3:media3-session:1.3.1` - медиа сессии
