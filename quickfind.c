#include <stdio.h>
#include <stdlib.h>

typedef struct quickfind {
	int  capacity;
	int* id;
} quickfind;

quickfind* quickfind_create(unsigned int n) {
	int id[n];
	for (unsigned int i = 0; i < n; i++) {
		id[i] = i;
	}

	quickfind* qf = malloc(sizeof(quickfind));

	if (qf == NULL) return NULL;

	qf->capacity = n;
	qf->id = id;

	return qf;
}

int quickfind_connected(quickfind* qf, unsigned int p, unsigned int q) {
	return qf->id[p] == qf->id[q];
}

void quickfind_union(quickfind* qf, unsigned int p, unsigned int q) {
	int pid = qf->id[p];
	int qid = qf->id[q];

	for (unsigned int i = 0; i < qf->capacity; i++)
		if (qf->id[i] == pid) qf->id[i] = qid;
}

int main(void) {
	quickfind* qf = quickfind_create(10);

	int size = 23;
	int data[23] = { 4, 3, 3, 8, 6, 5, 9, 4, 2, 1, 8, 9, 5, 0, 7, 2, 6, 1, 1, 0, 6, 7 };

	for (unsigned int i = 0; i < size - 1; i+=2) {
		if (!quickfind_connected(qf, data[i], data[i + 1])) {
			quickfind_union(qf, data[i], data[i+1]);
			printf("%d %d\n", data[i] , data[i + 1]);
		}
	}

	for (unsigned int i = 0; i < qf->capacity; i++) {
		printf("%d ", qf->id[i]);
	}
	printf("\n");

	if (qf != NULL) free(qf);

	return 0;
}
