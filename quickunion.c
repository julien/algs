#include <stdio.h>
#include <stdlib.h>

typedef struct quickunion {
	int  capacity;
	int* id;
} quickunion;

quickunion* quickunion_create(unsigned int n) {
	int id[n];
	for (unsigned int i = 0; i < n; i++) {
		id[i] = i;
	}

	quickunion* qu = malloc(sizeof(quickunion));

	if (qu == NULL) return NULL;

	qu->capacity = n;
	qu->id = id;

	return qu;
}

int quickunion_root(quickunion* qu, int i) {
	while (i != qu->id[i]) i = qu->id[i];
	return i;
}

int quickunion_connected(quickunion* qu, unsigned int p, unsigned int q) {
	return quickunion_root(qu, p) == quickunion_root(qu, q);
}

void quickunion_union(quickunion* qu, unsigned int p, unsigned int q) {
	int i = quickunion_root(qu, p);
	int j = quickunion_root(qu, q);
	qu->id[i] = j;
}

int main(void) {
	quickunion* qu = quickunion_create(10);

	int size = 23;
	int data[23] = { 4, 3, 3, 8, 6, 5, 9, 4, 2, 1, 8, 9, 5, 0, 7, 2, 6, 1, 1, 0, 6, 7 };

	for (unsigned int i = 0; i < size - 1; i+=2) {
		if (!quickunion_connected(qu, data[i], data[i + 1])) {
			quickunion_union(qu, data[i], data[i+1]);
			printf("%d %d\n", data[i] , data[i + 1]);
		}
	}

	for (unsigned int i = 0; i < qu->capacity; i++) {
		printf("%d ", qu->id[i]);
	}
	printf("\n");

	if (qu != NULL) free(qu);

	return 0;
}
