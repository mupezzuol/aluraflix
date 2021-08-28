-- Video to Category Mapping. (ManyToOne)
ALTER TABLE public.video ADD COLUMN category_id int8 NOT NULL;
ALTER TABLE public.video ADD CONSTRAINT fk_video_category FOREIGN KEY (category_id) REFERENCES category(id);