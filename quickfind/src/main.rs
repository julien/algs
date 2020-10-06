fn main() {
    let mut qf = QuickFind::new(10);
    println!("{:?}", qf);
    qf.union(1, 2);
    println!("{}", qf.connected(1, 2));
    qf.union(2, 3);
    println!("{}", qf.connected(2, 3));
    qf.union(3, 4);
    println!("{}", qf.connected(4, 3));
    println!("{:?}", qf);
}

#[derive(Debug)]
struct QuickFind {
    id: Vec<u32>,
}

impl QuickFind {
    fn new(n: u32) -> QuickFind {
        let id: Vec<u32> = (0..n).collect();
        QuickFind { id: id }
    }

    fn connected(&self, p: u32, q: u32) -> bool {
        self.id[p as usize] == self.id[q as usize]
    }

    fn union(&mut self, p: u32, q: u32) {
        let pid = self.id[p as usize];
        let qid = self.id[q as usize];

        for i in self.id.iter_mut() {
            if *i == pid {
                *i = qid;
            }
        }
    }
}
