import json

# Cargamos el archivo con las localidades.
with open('src/main/resources/localities/poblaciones.json', 'r', encoding="utf-8") as localities:
    data = json.load(localities)


# Extraer los nombres de las localidades
localities = [item['label'] for item in data]

# Guardar en un nuevo archivo JSON
with open('src/main/resources/localities/localities.json', 'w', encoding='utf-8') as output_file:
    json.dump(localities, output_file, ensure_ascii=False, indent=2)