ALTER TABLE template
  ADD COLUMN bind_type TEXT
  ,ADD COLUMN volume FLOAT
  ,ADD COLUMN weight FLOAT
  ,ADD COLUMN damage_per_turn FLOAT
  ,ADD COLUMN damage_shape TEXT
  ,ADD COLUMN damage_type TEXT
  ,ADD COLUMN slot TEXT
  ,ADD COLUMN swing_duration BIGINT
  ,ADD COLUMN ready_time BIGINT
;