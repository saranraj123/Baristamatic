INSERT INTO INGREDIENT_INVENTORY(INGREDIENT_NAME, AVAILABILITY, UNIT_COST) VALUES
  ('coffee', 10, 0.75),
  ('decaf_coffee', 10,0.75),
  ('sugar', 10,0.25),
  ('cream', 10,0.25),
  ('steamed_milk', 10,0.35),
  ('foamed_milk', 10,0.35),
  ('espresso', 10,1.10),
  ('cocoa', 10,0.90),
  ('whipped_cream', 10,1.00);
  
  INSERT INTO DRINK_INGREDIENT (DRINK_NAME, COFFEE,DECAF,SUGAR,CREAM,STEAMED_MILK,FOAMED_MILK,ESPRESSO,COCOA,WHIPPED_CREAM) VALUES
  ('coffee', 3,0,1,1,0,0,0,0,0),
  ('decaf coffee', 0,3,1,1,0,0,0,0,0),
  ('caffe latte', 0,0,0,0,1,0,2,0,0),
  ('caffe americano',0,0,0,0,0,0,3,0,0),
  ('caffe mocha',0,0,0,0,1,0,1,1,1),
  ('cappucino',0,0,0,0,1,1,2,0,0);
  
  
  
  