# EFP.CM.REPLACER
------------------
## Утилита для обновления конфигураций ЕФП адаптера

#### Данные проекта

Версия Java: 16

Для запуска необходимо иметь файлы:<br>
  &emsp; efp-cm-replacer-1.0.0.jar*
  &emsp; файл с новыми конфигурациями для ефп-адаптера с расширением .yml*
  &emsp; исходник проекта ефп-адаптера*
  
Запуск:<br>
java -jar efp-cm-replacer-1.0.0.jar -f={path-to-efp-project} -cm={path-to-file-with-new-configs}