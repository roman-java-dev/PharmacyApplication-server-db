namespace java communication

struct ProductThrift {
    1: i64 id,
    2: string name,
    3: double price,
    4: i32 quantity,
    5: string description
}

service ProductServiceThrift {
  ProductThrift add(1: ProductThrift productThrift),
  ProductThrift findByName(1: string name),
  list<ProductThrift> getAll(),
  ProductThrift update(1: i64 id, 2: ProductThrift productThrift),
  void delete(1: i64 id)
}
