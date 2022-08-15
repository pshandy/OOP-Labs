# Лабораторная работа №6. Использование полиморфных объектных указателей.
На основе созданной в предыдущем задании библиотеки графических фигур необходимо реализовать приложение для демонстрации основных возможностей полиморфных указателей. Для этого требуется создать простейший контейнер с возможностью занесения в него любых объектов-фигур. Простейший контейнер (иногда называемый коллекцией) можно создать на основе массива полиморфных указателей. Должны быть предусмотрены следующие операции:
- заполнение массива указателями на случайно выбранные фигуры; 
- показ всех фигур в массиве; 
- стирание всех фигур в массиве; 
- перемещение сразу всех фигур в новую базовую точку; 
- выборочная обработка только фигур из некоторого поддерева (например, перемещение только окружностей и эллипсов) за счет динамического определения типа объектного указателя при прохождении по массиву; 
- уничтожение массива фигур с освобождением памяти.
## Что должно быть:
1) Классы примитивов из работы 4 или 5.
2) В демонстрационном модуле объявлен массив со ссылками на объекты, наследуемые от абстрактного класса.
3) Более конкретно по функциям контейнера (массива) –показ и стирание фигур – сделать в классах примитивов, если не было ранее, функцию, обратную отрисовки, которая стирает с холста данную фигуру; перемещение в базовую точку можно сделать либо отдельным методом (в классах примитивов), либо через методы доступа к координатам центра; работа с отдельным поддеревом подразумевает использование is или instanceof, поэтому обработка, к примеру, сразу окружностей и эллипсов допускается (можно сделать и только обработку каждого отдельного класса).
4) Обязательно использовать в программе операторы типа is или instanceof при работе с подиерархией.
5) Демонстрационный модуль должен показать работу с коллекцией (массивом). Пользователь должен:
- создать массив из «10+номер по списку» случайных фигур;
- вызывать все функции по работе с массивом (показ всех фигур, стирание всех фигур, перемещение фигур в новую базовую точку, выборочная обработка, уничтожение массива);
- дополнительно у эллипсов (если они есть) вызывать уникальный метод, в противном случае вызвать уникальный метод другой фигуры;
- по нажатию стрелок на клавиатуре (или нажатию в угол экрана) перемещать на «10+номер по списку» пикселей все фигуры в соответствующем направлении.
![screenshot](/Lab6/example.png?raw=true)